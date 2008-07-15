package tmp.generated_capprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class FunctionParameter extends GenASTNode {
  public FunctionParameter(ArrayList<VarDeclToken> varDeclToken, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOneOrMore<VarDeclToken>("varDeclToken", varDeclToken)
    }, firstToken, lastToken);
  }
  public FunctionParameter(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new FunctionParameter(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<VarDeclToken> getVarDeclToken() {
    return ((PropertyOneOrMore<VarDeclToken>)getProperty("varDeclToken")).getValue();
  }
}
