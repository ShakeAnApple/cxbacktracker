# Oeritte

A tool for visual counterexample explanation for model checking

## Running

Requires Java version 12.0.2 or higher to be installed.

The latest jar for windows can be found in
`jars/%os%`

To run the tool double click the oeritte.jar or run it from the console
> java -jar jars/%os%/oeritte.jar

## Input data:
- a NuSMV model that follows NuSMV MODCHK format, where inputs of each module are annotated with their types (`%var_name% : boolean` for logic variables and `%var_name% : 0..100` for integers);
- LTL property (past-time operators and arrays are not supported);
- a file containing NuSMV verification output in the batch mode _or_ a file with the LTL formula and a file with the full verbose counterexample produced by NuSMV model checker for the provided LTL property.

## How to use:

When program is running:
1) provide input data in `Project` tab: click `Read from NuSMV output file` if you have NuSMV output from the batch mode or choose LTL formula and counterexample separately; click `Choose system model` and choose the model. In the "Info" section on the right, you can see the paths of the chosen files. Click "Load" if everything is all right, you will be redirected to "Workspace" tab.
2) Click on the step of interest in the first list on the right and the diagram and LTL formula will appear evaluated for the chosen step. By default the LTL formula will be explained for the step number zero and set of causes will be displayed in the second list on the right. Also formula explanation result is desplayed in the variables' table below the system diagram.
3) Now if you click on any step, the diagram and the LTL formula tree will be reevaluated. If you want to explain formula on particular step, click on this step and then click on the button `explain formula`. 
4) On the diagram there are blocks with columns of buttons (pins) on the left and right sides. There are also standalone pins on the left and right side of the diagram, they form input and output interfaces of the system correspondingly.
5) Diagram explanation: if you are to find causes of the particular input or output, press on the desired pin on the desired step. This will highlight explanation paths on the diagram. Also if input interface variables are causes for the explained pin, they will be displayed in the "Diagram explanation" list in form `stepNumber blockName varName varValue`.
6) To investigate contents of any module, double click on it and the module will be opened in another tab in the diagram area. Explanation here works the same way as described in 5.
7) Diagram variables' names can be found in tooltips that are triggered by hovering pins. In explanation mode if variable is a cause, information about it is added to its pin in form `stepNumber: varValue`.
8) To open another model repeat step 1.
