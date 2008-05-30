package tmp.generated_fj;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class FormalParameterRest extends GenASTNode {
  public FormalParameterRest(FormalParameter formalParameter, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<FormalParameter>("formalParameter", formalParameter)
    }, firstToken, lastToken);
  }
  public FormalParameterRest(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new FormalParameterRest(cloneProperties(),firstToken,lastToken);
  }
  public FormalParameter getFormalParameter() {
    return ((PropertyOne<FormalParameter>)getProperty("formalParameter")).getValue();
  }
}
