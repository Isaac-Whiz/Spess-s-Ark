package com.whizstudios.spessark.code;

import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class CodeService implements CodeDAO {

    private final CodeRepository codeRepository;

    public CodeService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    @Override
    public boolean validateCode(String codeQuery) {
        return codeRepository.findAll()
                .stream().
                filter(code -> Objects.equals(code.getCode(), codeQuery))
                .findFirst()
                .get().isUsed();
    }

    @Override
    public void updateCode(String codeQuery) {
        var codeId = getId(codeQuery);
        var code = codeRepository.findById(codeId).get();
        code.setUsed(true);
        code.setId(codeId);
        codeRepository.save(code);
    }

    public String generateAdminCode() {
        var code = CodeGenerator.generateAdminCode();
        codeRepository.save(code);
        return code.getCode();
    }

    public String generateUserCode() {
        var code = CodeGenerator.generateUserCode();
        codeRepository.save(code);
        return code.getCode();
    }

    private Long getId(String codeQuery) {
        return codeRepository.findAll()
                .stream()
                .filter(code -> Objects.equals(code.getCode(), codeQuery))
                .findFirst()
                .get()
                .getId();
    }
}
