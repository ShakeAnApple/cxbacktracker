package shakeanapple.backtracker;

import shakeanapple.backtracker.common.XmlSerializer;
import shakeanapple.backtracker.parser.basiccomponents.xmlmodel.*;


public class Main {

    public static void main(String[] args)  {
        MainFx.main(args);

//        List<InputVariable> inputs = new ArrayList<>();
//        inputs.add(new InputVariable(1, VarType.BOOLEAN, "in1"));
//        inputs.add(new InputVariable(2, VarType.BOOLEAN, "in2"));
//
//        List<OutputVariable> outputs = new ArrayList<>();
//        outputs.add(new OutputVariable(3, VarType.BOOLEAN, "out1", "false"));
//        outputs.add(new OutputVariable(4, VarType.BOOLEAN, "out2", "true"));
//
//        List<BasicComponentAbstract> components = new ArrayList<>();
//        List<Choice> choices = new ArrayList<>();
//        OutputVariable outchmain = new OutputVariable(9, VarType.BOOLEAN, "outchmain");
//
//        InputVariable inch1 = new InputVariable(5, VarType.BOOLEAN, "inch1");
//        OutputVariable outch1 = new OutputVariable(6, VarType.BOOLEAN, "outch1");
//        choices.add(new Choice(inch1, outch1));
//
//        InputVariable inch2 = new InputVariable(7, VarType.BOOLEAN, "inch2");
//        OutputVariable outch2 = new OutputVariable(8, VarType.BOOLEAN, "outch2");
//        choices.add(new Choice(inch2, outch2));
//
//        components.add(new ChoiceComponent(ComponentType.BOOL_CHOICE, choices, outchmain));
//
//        List<InputVariable> c2inputs = new ArrayList<>();
//        c2inputs.add(new InputVariable(10, VarType.BOOLEAN, "inand1"));
//        c2inputs.add(new InputVariable(11, VarType.BOOLEAN, "inand2"));
//
//        List<OutputVariable> c2outputs = new ArrayList<>();
//        c2outputs.add(new OutputVariable(12, VarType.BOOLEAN, "outand"));
//
//        components.add(new BasicComponent(ComponentType.AND, c2inputs, c2outputs));
//
//        List<OutputVariable> c3outputs = new ArrayList<>();
//
//        List<InputVariable> c3inputs = new ArrayList<>();
//        c3inputs.add(new InputVariable(13, VarType.BOOLEAN, "inor1"));
//        c3inputs.add(new ConstantInput(14, VarType.BOOLEAN, "inor2", "true"));
//
//        c3outputs.add(new OutputVariable(15, VarType.BOOLEAN,"outor"));
//
//        components.add(new BasicComponent(ComponentType.OR, c3inputs, c3outputs));
//
//        List<Connection> connections = new ArrayList<>();
//        connections.add(new Connection(1,6));
//        connections.add(new Connection(1,8));
//        connections.add(new Connection(1,11, "true"));
//        connections.add(new Connection(2,5));
//        connections.add(new Connection(2,10, "true"));
//        connections.add(new Connection(12,7));
//        connections.add(new Connection(9,3));
//        connections.add(new Connection(9,13));
//        connections.add(new Connection(15,14));
        //Block b = new Block("Flipflop", inputs, outputs, components, connections);


//        XmlSerializer.serializeToXML(b);

        //Block bdes = XmlSerializer.deserializeFromXML("C:\\Users\\ovsianp1\\tmp\\t.xml");
//        Project pr = XmlSerializer.deserializeFromXML("C:\\Users\\ovsianp1\\projects\\codesys\\Test2.xml");
//        int a = 1;
        //LtlFormula.parse("G(((!(alarm) & !(criteria)) & X (criteria & !(ack_button))) -> X (alarm))");
    }
}
