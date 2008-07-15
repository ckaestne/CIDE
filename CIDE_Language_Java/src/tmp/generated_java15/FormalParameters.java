package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class FormalParameters extends GenASTNode {
  public FormalParameters(FormalParametersInternal formalParametersInternal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<FormalParametersInternal>("formalParametersInternal", formalParametersInternal)
    }, firstToken, lastToken);
  }
  public FormalParameters(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new FormalParameters(cloneProperties(),firstToken,lastToken);
  }
  public FormalParametersInternal getFormalParametersInternal() {
    return ((PropertyZeroOrOne<FormalParametersInternal>)getProperty("formalParametersInternal")).getValue();
  }
}
