### Intellij-idea'da proje ayağa kalkarken aşağıdaki map-struct hatası alınırsa:
java: Internal error in the mapping processor: java.lang.NullPointerException  	at org.mapstruct.ap.internal.processor.DefaultVersionInformation.createManifestUrl(DefaultVersionInformation.java:182)

file -> Settings -> build & Execution deployment -> Compiler -> Shared build process VM option alanına aşağıdaki komut eklenmelidir.
- Djps.track.ap.dependencies=false
- https://stackoverflow.com/questions/65112406/intellij-idea-mapstruct-java-internal-error-in-the-mapping-processor-java-lang





### EMPLOYEE oluştururken tanımlanmış 2 adet role vardır:
- EMPLOYEE 
- ADMIN

bu roller employee/create apisine buyuk harfler ile gönderilmelidir.