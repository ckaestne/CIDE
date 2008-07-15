package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Cast extends GenASTNode {
  public Cast(FunctionReturnType functionReturnType, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<FunctionReturnType>("functionReturnType", functionReturnType)
    }, firstToken, lastToken);
  }
  public Cast(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Cast(cloneProperties(),firstToken,lastToken);
  }
  public FunctionReturnType getFunctionReturnType() {
    return ((PropertyOne<FunctionReturnType>)getProperty("functionReturnType")).getValue();
  }
}
