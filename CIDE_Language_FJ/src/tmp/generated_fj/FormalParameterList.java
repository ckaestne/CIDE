package tmp.generated_fj;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class FormalParameterList extends GenASTNode {
  public FormalParameterList(FormalParameter formalParameter, ArrayList<FormalParameterRest> formalParameterRest, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<FormalParameter>("formalParameter", formalParameter),
      new PropertyZeroOrMore<FormalParameterRest>("formalParameterRest", formalParameterRest)
    }, firstToken, lastToken);
  }
  public FormalParameterList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new FormalParameterList(cloneProperties(),firstToken,lastToken);
  }
  public FormalParameter getFormalParameter() {
    return ((PropertyOne<FormalParameter>)getProperty("formalParameter")).getValue();
  }
  public ArrayList<FormalParameterRest> getFormalParameterRest() {
    return ((PropertyZeroOrMore<FormalParameterRest>)getProperty("formalParameterRest")).getValue();
  }
}
