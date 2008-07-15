package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class memoryInitializer extends GenASTNode {
  public memoryInitializer(ASTStringNode identifier, FunctionParameterList functionParameterList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("identifier", identifier),
      new PropertyZeroOrOne<FunctionParameterList>("functionParameterList", functionParameterList)
    }, firstToken, lastToken);
  }
  public memoryInitializer(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new memoryInitializer(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getIdentifier() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier")).getValue();
  }
  public FunctionParameterList getFunctionParameterList() {
    return ((PropertyZeroOrOne<FunctionParameterList>)getProperty("functionParameterList")).getValue();
  }
}
