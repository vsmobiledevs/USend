#########################################################################################
#                   HOW TO IMPLEMENT ROOM DAO WITH LIVEDATA                             #
#########################################################################################

Room is a Database Object Mapping library that makes it easy to access database on
Android applications.


Getting started
-----------------------------------------------------------------------------------------
-------------------------------------DAO-------------------------------------------------
DAOs are responsible for defining the methods that access the database. In the initial
SQLite implementation of our project, all the queries to the database were done in the
LocalUserDataSource file, where we were working with Cursor objects. With Room, we don’t
need all the Cursor related code and can simply define our queries using annotations in
the UserDao class.


-----------------Important Things to remember when add dependency-----------------------
Declaring dependencies(App Level Gradle)
    implementation "androidx.room:room-runtime:$room_version"
    annotationProcessor "androidx.room:room-compiler:$room_version" // For Kotlin use kapt instead of annotationProcessor

    /*** Only For Kotlin ***/
    apply plugin: 'kotlin-kapt'
    kapt "androidx.room:room-compiler:${rootProject.room_version}"



1.  For Kotlin use kapt instead of annotationProcessor
    kapt "androidx.room:room-compiler:${rootProject.room_version}"

2.  If you get this error : -
Error -    Gradle DSL method not found: 'kapt()'
           Possible causes:
           The project 'basecode_arch' may be using a version of the Android Gradle plug-in that does not contain the method (e.g. 'testCompile' was added in 1.1.0).

Solution - Include below line at the top of (App Level Gradle)
           apply plugin: 'kotlin-kapt'


#######################################How to Use#########################################
-----------------------------------------------------------------------------------------

 getAllBeans() - Returns LiveData<UserBean>

val liveDataUserBean = AppDatabase.getInstance(this)?.getAppDao()?.getAllBeans()

liveDataUserBean?.observeForever { list ->
            if (list.size == 0) txt_console.text = "No Data Available in Database"
            list.forEach {
                val userBean = it
                JLog.i("MainActivity.", userBean.id)
            }
        }
-----------------------------------------------------------------------------------------
AppDatabase.getInstance(this)?.getAppDao()?.insert(
                UserBean(
                    name = "ABC",
                    age = "12",
                    birthDate = "10/12/1992",
                    image = "No Image",
                    profession = "Service"
                )
            )
-----------------------------------------------------------------------------------------


//use when you want to copy database from assets to database location
        try {
            boolean isCreated = AppDatabase.copyDatabaseFromAssets(this);
        } catch (IOException e) {
            e.printStackTrace();
        }



//How to do database opration. See below code as example

AppDatabase.getInstance(this).getAppDao().insert(new LoginBean());

AppDatabase.getInstance(this).getAppDao().insert(new UserBean());
//use when you want to copy database from assets to database location
        try {
            boolean isCreated = AppDatabase.copyDatabaseFromAssets(this);
        } catch (IOException e) {
            e.printStackTrace();
        }



//How to do database opration. See below code as example

AppDatabase.getInstance(this).getAppDao().insert(new LoginBean());

AppDatabase.getInstance(this).getAppDao().insert(new UserBean());