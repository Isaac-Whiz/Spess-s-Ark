<br />

  # Spess' Ark Api
  Inspired by love to create something new and intruiging, this application was crafted.

## About The Project
This is backend service of an application that serves the roles of creating, deleting, updating, persisting and processing information for the user to enable storage of
student information efficient.

### Built With

* [Java](https://www.bing.com/ck/a?!&&p=0a6be56bf891c859JmltdHM9MTcwOTA3ODQwMCZpZ3VpZD0wZjUxNzJjZi1jYTAzLTY4ODQtMDFhOC02MWY0Y2I5NTY5YzMmaW5zaWQ9NTQ5Mw&ptn=3&ver=2&hsh=3&fclid=0f5172cf-ca03-6884-01a8-61f4cb9569c3&psq=java&u=a1aHR0cHM6Ly9qYXZhLmNvbS9lbi9kb3dubG9hZC8&ntb=1)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [IntelliJ Idea IDE](https://www.jetbrains.com/idea/)
* [Docker](https://docs.docker.com/)
* [PostgreSQL](https://www.postgresql.org/)
* [Git](https://github.com/)

<!-- GETTING STARTED -->
## Getting Started
There is more than one way to get this api up and running, these are explored below.
The fisrt and most quick is to clone this repository and open it from any preferred code editor then compile and run.
Secondly, you need to containerize the application and then run the container. 
Both these strategies are explained in details below.
### Prerequisites
Different requirements are needed for each strategy.
- [ ] Running from an IDE (code editor)
   <p>This will require installing the following technologies; </p>
* Java
* Docker
* Git
* PostgreSQL
* IDE (Eclipse, IntelliJ (recommended), Vscode extra)
- [ ] Running a Docker container.
   <p>For this, install the rest of the above except docker.</p>

### Installation

_After successful installation of the above technologies, proceed to the following instructions._
1. Open search and type _SQL shell (psql)_ and open it, on the server leae it at localhost, database also at postgres, port at 5432, for the
   username, it accords if you set a preferred custom name (during installation) then use that else leave it on the default (postgres), and 
   lastly for the password, use the one you set.
2. Create a database using the below command.
   ```
   CREATE DATABASE spess;
   ```
3. Open terminal and ```cd ``` command to navigate to the preferred location to store the program.
4. Clone the repo by running the following command
   ```sh
   git clone https://github.com/Isaac-Whiz/Spess-s-Ark.git
   ```
5. Open the IDE and navigate to the application directory.
   _Note: For those willing to run the application using the IDE, execute the next step (step 6) and Docker users then skip it to 7._
6. Add the following environment variables. Tap [here](https://www.youtube.com/watch?v=oYfd9pDXip8) to watch a video for on how to add them (IntelliJ users). Below are the environment variables.
   ```
   admin_name=Super_User
   admin_password=spesspass
   admin_url=http://localhost:1234/admin/spess
   app_port=2007
   db_password=changeMe
   db_url=jdbc:postgresql://localhost:5432/spess
   db_username=changeMeAlso
   frontend_url=http://localhost:1234
   mail_host=smtp.gmail.com
   mail_password=esfdbbasjoldvubd
   mail_port=587
   mail_username=whizstudios256@gmail.com
   SECRET_KEY=vbbbbbbbbbbbbbbbbbbbbbfgsrtreonireiosdfgfgvdgfhgnnlbnbihishioejrrjjewrwwtuipwuweofjnjnjfnasndfnjsgnjngjkgnjkgnkjnosgggngjknfgdfjkgnfgnfgsgkngfjkgngnnustguibjdfwfweurrurhwhubbggbueruhuiwbgisgibdfbggfb
   test_url=http://localhost:2007
   ```
   _Note: eliminate the equals `(=)` when add the above variables and change the `db_password` and `db_username` to the ones you used 
   while creating the database._
   You can now run the application by tapping the play button or using `Shift + F10` on Windows/Linux or `Ctrl + D` on Mac.
   Check the logs and ensure that you see a log saying `Tomcat is running on port 2007`. 🎉🎊🪅 Success the application is app and running.
   Then tap [here](https://github.com/Isaac-Whiz/Spess-Frontend) to proceed to the frontend.
7. From this step, is for those whom are using the Docker deamon. Open the terminal and run the following command.
   ```
   mvn compile com.google.cloud.tools:jib-maven-plugin:3.4.4:build -Dimage=isaacwhiz/spess-ark-v3-6 -Djib.container.environment=admin_name=Super_User,admin_password=spesspass,admin_url=http://localhost:1234/admin/spess,app_port=2007,db_password=changeMe,db_url=jdbc:postgresql://localhost:5432/spess,db_username=changeMeToo,frontend_url=http://localhost:1234,mail_host=smtp.gmail.com,mail_password=esfdbbasjoldvubd,mail_port=587,mail_username=whizstudios256@gmail.com,SECRET_KEY=vbbbbbbbbbbbbbbbbbbbbbfgsrtreonireiosdfgfgvdgfhgnnlbnbihishioejrrjjewrwwtuipwuweofjnjnjfnasndfnjsgnjngjkgnjkgnkjnosgggngjknfgdfjkgnfgnfgsgkngfjkgngnnustguibjdfwfweurrurhwhubbggbueruhuiwbgisgibdfbggfb,test_url=http://localhost:2007

   ```
   _Note: Change the `db_password` and `db_username` to the ones you used when creating the database._
   Then run the following command to spin a container on that image.
   ```
   docker run -d -p 2007:2007 --name spess-container isaacwhiz/spess-ark-v3-6

   ```
   After getting the container up and running, execute the following command to check the logs. `docker logs -f spess-container` it should show
   a log saying `Tomcat is running on port 2007`. 🎉🎊🪅 Success the application is app and running.
   Then tap [here](https://github.com/Isaac-Whiz/Spess-Frontend) to proceed to the frontend.
   
## Usage

For future usage, those using the IDE simply run the application through the play button or the shortcut and the Docker users simply press the play button on the container with name `spess-container`

## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "bug fix" for example.
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingBugFix`)
3. Commit your Changes (`git commit -m 'Add some AmazingBugFix'`)
4. Push to the Branch (`git push origin feature/AmazingBugFix`)
5. Open a Pull Request

## License

Distributed under the MIT License. See `LICENSE.txt` for more information.

## Contact
Ssekajja Wavamuno Isaac - [@IsaacWavamuno](https://twitter.com/Isaac_Whiz) - ssekajjawavamuno@gmail.com

Project Link: [https://github.com/Isaac-Whiz/SDA_Nyimba](https://github.com/Isaac-Whiz/Spess-Frontend)
