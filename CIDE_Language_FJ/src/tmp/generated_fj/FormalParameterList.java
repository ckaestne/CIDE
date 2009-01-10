package tmp.generated_fj;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class FormalParameterList extends GenASTNode {
  public FormalParameterList(ArrayList<FormalParameter> formalParameter, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<FormalParameter>("formalParameter", formalParameter)
    }, firstToken, lastToken);
  }
  public FormalParameterList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new FormalParameterList(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<FormalParameter> getFormalParameter() {
    return ((PropertyList<FormalParameter>)getProperty("formalParameter")).getValue();
  }
}
