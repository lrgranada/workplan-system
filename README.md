# workplan-system
Simple app to set start and end date of a task given a duration

Aside from the given parameters of Task and duration, I also included a project id.
In real world, this project id should accommodate tasks.
As an input, project id can be specified in the application.properties file, while task should be added in a Task table in database with the corresponding project id.
Included in the github is the table design in text format (Tables.txt).

2 tables were involved in this small app.
TASK - A table that holds the tasks for projects.
TASK_HOLIDAY - A table containing holidays.

Scope:
Calculate the start and end date of a given task in a project.
Able to compute the start and end date of projects so long as project id is specified in the application.properties file.
Considers holidays and weekends in the calculation of start and end date.

Limitations:
App's performance may be affected for longer task durations.
Prints the tasks' start and end date in the console and will not provide a graphical or tabular output.
Improper set up of tasks may cause an ungraceful exit of the app.
Dependency column in the Task table is comma(,) delimited. Other delimiters may cause the app to fail.
