![http://www2.hawaii.edu/~dwilkie/machu_picchu_pichu.jpg](http://www2.hawaii.edu/~dwilkie/machu_picchu_pichu.jpg)
<br>
<font size='1'>Macchu-Picchu-Pichu, our beloved mascot</font>

Our objective is to develop and improve upon a command line interface (CLI) program to understand various aspects of energy use in the Hale Aloha residence halls.  This project will also be an assessment of the project contributors' abilities regarding Issue Driven Project Management, Continuous Integration, and testing.  More information can be found at this <a href='https://sites.google.com/site/ics314fall2011/modules/issue-driven-project-management'>website</a>.<br>
<br>
Users should follow the directions on the <a href='http://code.google.com/p/hale-aloha-cli-pichu/wiki/UserGuide'>UserGuide</a>.<br>
Developers should refer to the <a href='http://code.google.com/p/hale-aloha-cli-pichu/wiki/DeveloperGuide'>DeveloperGuide</a>.<br>
<br>
The following invokes the program and shows the output of the <code>help</code> and <code>quit</code> commands:<br>
<br>
<br>
<br>
<pre><code>bash-3.2$ java -jar hale-aloha-cli-pichu.jar<br>
Connected successfully to the Hale Aloha WattDepot server.<br>
&gt; help<br>
ARGUMENTS:<br>
  [tower | lounge] =&gt;<br>
    [tower] = Mokihana | Ilima | Lehua | Lokelani<br>
    [lounge] = Mokihana | Ilima | Lehua | Lokelani<br>
      followed by "-" followed by A | B | C | D | E.<br>
      For example: Mokihana-A<br>
  [date] =&gt;<br>
    The date in yyyy-mm-dd format.<br>
    Must be BEFORE today's date.<br>
  [start] =&gt;<br>
    The starting date in yyyy-mm-dd format.<br>
    Must be BEFORE [end], which is BEFORE today's date.<br>
  [end] =&gt;<br>
    The ending date in yyyy-mm-dd format.<br>
    Must be AFTER [start] and BEFORE today's date.<br>
<br>
COMMANDS:<br>
Here are the available commands for this system.<br>
<br>
current-power<br>
  Usage: current-power [tower | lounge]<br>
    Retrieves the current power of the particular source.<br>
<br>
daily-energy<br>
  Usage: daily-energy [tower | lounge] [date]<br>
    Retrieves the energy consumed by the source from<br>
    the date specified by the user.<br>
<br>
energy-since<br>
  Usage: energy-since [tower | lounge] [date]<br>
    Retrieves the energy consumed by the source from the date<br>
    specified by the user to the time of the latest sensor data.<br>
<br>
rank-towers<br>
  Usage: rank-towers [start] [end]<br>
    Retrieves a list in sorted order from least to most energy consumed<br>
    between the [start] and [end] dates specified by the user.<br>
<br>
set-baseline<br>
  Usage: set-baseline [tower | lounge] [date]<br>
    This command defines [date] as the "baseline" day for tower or lounge.<br>
    When this command is executed, the system should obtain and save the]<br>
    amount of energy used during each of the 24 hours of that day for the<br>
    given tower or lounge.  <br>
<br>
monitor-goal<br>
  Usage: monitor-goal [tower | lounge] [goal] [interval]<br>
    Retrieves the power consumed by the source.<br>
<br>
<br>
monitor-power<br>
  Usage: monitor-power [tower | lounge] [interval]<br>
    This command prints out a timestamp and the current power for<br>
    [tower | lounge] every [interval] seconds.  [interval] is an optional<br>
    integer greater than 0 and defaults to 10 seconds. Entering any character<br>
    (such as a carriage return) stops this monitoring process and returns the<br>
    user to the command loop.<br>
<br>
help<br>
  Usage: help<br>
    Provides a brief description of each command and its usage.<br>
<br>
quit<br>
  Usage: quit<br>
    Terminates execution.<br>
&gt; quit<br>
Concerned about energy and power?  You're awesome!  See ya!<br>
</code></pre>