package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class FunctionParameterList extends GenASTNode {
  public FunctionParameterList(ArrayList<FunctionParameter> functionParameter, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<FunctionParameter>("functionParameter", functionParameter)
    }, firstToken, lastToken);
  }
  public FunctionParameterList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new FunctionParameterList(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<FunctionParameter> getFunctionParameter() {
    return ((PropertyList<FunctionParameter>)getProperty("functionParameter")).getValue();
  }
}
