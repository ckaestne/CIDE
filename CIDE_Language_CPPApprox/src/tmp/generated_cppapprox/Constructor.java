package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Constructor extends GenASTNode {
  public Constructor(ConstructorHeader constructorHeader, FunctionParameterList functionParameterList, ConstructorInitializer constructorInitializer, BlockOrSemi blockOrSemi, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ConstructorHeader>("constructorHeader", constructorHeader),
      new PropertyZeroOrOne<FunctionParameterList>("functionParameterList", functionParameterList),
      new PropertyZeroOrOne<ConstructorInitializer>("constructorInitializer", constructorInitializer),
      new PropertyOne<BlockOrSemi>("blockOrSemi", blockOrSemi)
    }, firstToken, lastToken);
  }
  public Constructor(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Constructor(cloneProperties(),firstToken,lastToken);
  }
  public ConstructorHeader getConstructorHeader() {
    return ((PropertyOne<ConstructorHeader>)getProperty("constructorHeader")).getValue();
  }
  public FunctionParameterList getFunctionParameterList() {
    return ((PropertyZeroOrOne<FunctionParameterList>)getProperty("functionParameterList")).getValue();
  }
  public ConstructorInitializer getConstructorInitializer() {
    return ((PropertyZeroOrOne<ConstructorInitializer>)getProperty("constructorInitializer")).getValue();
  }
  public BlockOrSemi getBlockOrSemi() {
    return ((PropertyOne<BlockOrSemi>)getProperty("blockOrSemi")).getValue();
  }
}
