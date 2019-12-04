# SQLI-echallenge

2019 challenge in Android was about creating a TODO list application, using a single List.
- 2 activities  
  |-- TODOS Activity (`MainActivity`)  
  |-- ADD New Todo (`AddTodoActivity`)    
  
- Once user open the application he should find a default list of todos.  
- The state of the list should be saved, it means that even if the user exists the application, the last modifications should be displayed.  
- In the add new todo activity, you should test if the submited todo already exists, if it's so you should display a message, else you should add it the todos list and redirect the user to the main page (`AddTodoActivity`)  
## Helper 
This class simplifies calls to SharedPreferences in a line of code. It can also do more like: saving a list of strings, integers and saving images. All in 1 line of code!  
[TinyDB Github](https://github.com/kcochibili/TinyDB--Android-Shared-Preferences-Turbo) 
