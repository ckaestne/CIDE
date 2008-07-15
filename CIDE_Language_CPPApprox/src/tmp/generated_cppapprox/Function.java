package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Function extends GenASTNode {
  public Function(FunctionHeader functionHeader, FunctionParameterList functionParameterList, BlockOrSemi blockOrSemi, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<FunctionHeader>("functionHeader", functionHeader),
      new PropertyZeroOrOne<FunctionParameterList>("functionParameterList", functionParameterList),
      new PropertyOne<BlockOrSemi>("blockOrSemi", blockOrSemi)
    }, firstToken, lastToken);
  }
  public Function(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Function(cloneProperties(),firstToken,lastToken);
  }
  public FunctionHeader getFunctionHeader() {
    return ((PropertyOne<FunctionHeader>)getProperty("functionHeader")).getValue();
  }
  public FunctionParameterList getFunctionParameterList() {
    return ((PropertyZeroOrOne<FunctionParameterList>)getProperty("functionParameterList")).getValue();
  }
  public BlockOrSemi getBlockOrSemi() {
    return ((PropertyOne<BlockOrSemi>)getProperty("blockOrSemi")).getValue();
  }
}
