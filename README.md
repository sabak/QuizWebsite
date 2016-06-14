# QuizWebsite
Final project in OOP

Repository created by sabak (Saba Kharabadze) 
Team: Nini Mumladze, Alexander Turdzeladze, Giorgi Kvantrishvili, Saba Kharabadze

###[**Wiki**] **როგორ დავიწყოთ პროექტზე მუშაობა**

1. პროექტზე სამუშაოდ ვიყენებთ **IntelliJ IDEA Ultimate**-ს (Community Edition-ს არ აქვს ვებ აპლიკაციების მხარდაჭერა)

1. IntelliJ IDEA-ში უკვე უნდა გქონდეთ დამატებული **Tomcat**, პროექტის კონფიგურაციაში ამჟამად გაწერილია **Tomcat 8** ვერსია. პროექტმა რომ სწორად იმუშაოს თქვენთან, ვალდებული ხართ, რომ IntelliJ IDEA-ზე გეყენოთ Tomcat 8.* ვერსია და სახელად ერქვას ზუსტად **Tomcat 8**, ისე როგორც აქ არის ნაჩვენები:
    ![alt text](https://raw.githubusercontent.com/sabak/QuizWebsite/master/misc/server_preferences.png "Server Preferences")

2. IntelliJ IDEA-ს საშუალებით GitHub-იდან დაკლონეთ პროექტი. დარწმუნდით, რომ პროექტის Tomcat-ით გაშვება შესაძლებელია:

    ![alt text](https://raw.githubusercontent.com/sabak/QuizWebsite/master/misc/run_options.png "Run Options")
    ![alt text](https://raw.githubusercontent.com/sabak/QuizWebsite/master/misc/run_config.png "Run Config")
    ![alt text](https://raw.githubusercontent.com/sabak/QuizWebsite/master/misc/server_config.png "Server Config")

3. web/META-INF საქაღალდეში მყოფ context.xml ფაილში (რომელიც Connection Pool-ის პარამეტრებს წარმოადგენს) მონაცემთა ბაზის მომხმარებლის სახელი და/ან პაროლი სასურველი მონაცემებით უნდა შეცვალოთ. **აუცილებელია** ფაილს მიუთითოთ **git update-index --assume-unchanged <file>**, რათა სხვა კოლაბორატორებთან ამ ფაილზე მუდმივი კონფლიქტი არ მოხდეს.

4. DBInfo.java ფაილში კონსტანტების მნიშვნელობები - მონაცემთა ბაზის მომხმარებლის სახელი და/ან პაროლი სასურველი მონაცემებით უნდა შეცვალოთ. **აუცილებელია** ფაილს მიუთითოთ **git update-index --assume-unchanged <file>**, რათა სხვა კოლაბორატორებთან ამ ფაილზე მუდმივი კონფლიქტი არ მოხდეს.

5. ბაზის სქემად გამოიყენეთ QuizWebsiteSchema.sql ფაილი (database დირექტორიიდან)
