package shakeanapple.backtracker.core.diagramexplanation.tonusmv;

import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockBase;
import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockComplex;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.AssignFunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.CountFunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.DelayFunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.arithmetic.*;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.choice.ChoiceFunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.logic.*;
import shakeanapple.backtracker.core.diagramexplanation.tonusmv.blocksconverters.ComplexBlockConverter;
import shakeanapple.backtracker.core.diagramexplanation.tonusmv.blocksconverters.basics.*;

class ConvertersFabric {

    NusmvBlockConverterBase getConverterForBlock(FunctionBlockBase block){
        if (block instanceof FunctionBlockComplex){
            return new ComplexBlockConverter((FunctionBlockComplex) block);
        } else if (block instanceof DivFunctionBlockBasic) {
            return new DivBasicBlockConverter((DivFunctionBlockBasic) block);
        } else if (block instanceof MinusFunctionBlockBasic) {
            return new MinusBasicBlockConverter((MinusFunctionBlockBasic) block);
        } else if (block instanceof ModFunctionBlockBasic) {
            return new ModBlockConverter((ModFunctionBlockBasic) block);
        } else if (block instanceof MulFunctionBlockBasic) {
            return new MulBasicBlockConverter((MulFunctionBlockBasic) block);
        } else if (block instanceof PlusFunctionBlockBasic) {
            return new PlusBasicBlockConverter((PlusFunctionBlockBasic) block);
        } else if (block instanceof ChoiceFunctionBlockBasic) {
            return new ChoiceBasicBlockConverter((ChoiceFunctionBlockBasic) block);
        } else if (block instanceof AndFunctionBlockBasic) {
            return new AndBasicBlockConverter((AndFunctionBlockBasic) block);
        } else if (block instanceof EqFunctionBlockBasic) {
            return new EqBasicBlockConverter((EqFunctionBlockBasic) block);
        } else if (block instanceof GreaterEqFunctionBlockBasic) {
            return new GreaterEqBasicBlockConverter((GreaterEqFunctionBlockBasic) block);
        } else if (block instanceof GreaterFunctionBlockBasic) {
            return new GreaterBasicBlockConverter((GreaterFunctionBlockBasic) block);
        } else if (block instanceof LessEqFunctionBlockBasic) {
            return new LessEqBasicBlockConverter((LessEqFunctionBlockBasic) block);
        } else if (block instanceof LessFunctionBlockBasic) {
            return new LessBasicBlockConverter((LessFunctionBlockBasic) block);
        } else if (block instanceof OrFunctionBlockBasic) {
            return new OrBasicBlockConverter((OrFunctionBlockBasic) block);
        } else if (block instanceof AssignFunctionBlockBasic) {
            return new AssignBasicBlockConverter((AssignFunctionBlockBasic) block);
        } else if (block instanceof CountFunctionBlockBasic) {
            return new CountBasicBlockConverter((CountFunctionBlockBasic) block);
        } else if (block instanceof DelayFunctionBlockBasic) {
            return new DelayBasicBlockConverter((DelayFunctionBlockBasic) block);
        }
        throw new RuntimeException("No converter for the block of type: " + block.getType());
    }
}
