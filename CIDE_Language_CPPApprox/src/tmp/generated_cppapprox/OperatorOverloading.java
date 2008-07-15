package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class OperatorOverloading extends GenASTNode {
  public OperatorOverloading(OperatorHeader operatorHeader, AnyOpSymbol anyOpSymbol, FunctionParameterList functionParameterList, BlockOrSemi blockOrSemi, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<OperatorHeader>("operatorHeader", operatorHeader),
      new PropertyOne<AnyOpSymbol>("anyOpSymbol", anyOpSymbol),
      new PropertyZeroOrOne<FunctionParameterList>("functionParameterList", functionParameterList),
      new PropertyOne<BlockOrSemi>("blockOrSemi", blockOrSemi)
    }, firstToken, lastToken);
  }
  public OperatorOverloading(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new OperatorOverloading(cloneProperties(),firstToken,lastToken);
  }
  public OperatorHeader getOperatorHeader() {
    return ((PropertyOne<OperatorHeader>)getProperty("operatorHeader")).getValue();
  }
  public AnyOpSymbol getAnyOpSymbol() {
    return ((PropertyOne<AnyOpSymbol>)getProperty("anyOpSymbol")).getValue();
  }
  public FunctionParameterList getFunctionParameterList() {
    return ((PropertyZeroOrOne<FunctionParameterList>)getProperty("functionParameterList")).getValue();
  }
  public BlockOrSemi getBlockOrSemi() {
    return ((PropertyOne<BlockOrSemi>)getProperty("blockOrSemi")).getValue();
  }
}
