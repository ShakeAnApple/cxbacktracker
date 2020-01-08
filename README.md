# Oeritte

A tool for visual counterexample explanation for model checking

## Running

Requires Java version 12.0.2 or higher to be installed.

The latest jar for windows can be found in
`jars/win`

To run the tool double click the oeritte.jar or run it from the console
> java -jar jars/win/oeritte.jar

## Input data:
- a flat model (the main module with nested basic blocks) that follows NuSMV MODCHK format;
- LTL property (past-time operators and arrays are not supported);
- a full verbose counterexample produced by NuSMV model checker for the provided LTL property.

## How to use:

When program is running:
1) provide input data: press `File` menu button and then choose model and counterexample files with `Open model` and `Open counterexample`, input LTL formula with `Input formula`.
2) When all the required data provided click on the step of interest in the second list on the right and the diagram and LTL formula will appear evaluated for the chosen step. By default in the third list on the right LTL formula will be explained for the step number zero and set of causes will be displayed in the third list on the right. Also formula explanation result is desplayed in the variables' table below the system diagram.
3) Now if you click on any step, the diagram and the LTL formula tree will be reevaluated. If you want to explain formula on particular step, click on this step and then click on the button `explain formula`. 
4) On the diagram there are blocks with columns of buttons (pins) on the left and right sides. There are also standalone pins on the left and right side of the diagram, they form input and output interfaces of the system correspondingly.
5) Diagram explanation: if you are to find causes of the particular input or output, press on the desired pin on the desired step. This will highlight explanation paths on the diagram. Also if input interface variables are causes for the explained pin, they will be displayed in the first list on the right in a form `stepNumber blockName varName varValue`.
6) To open another model just repeat step 1.
