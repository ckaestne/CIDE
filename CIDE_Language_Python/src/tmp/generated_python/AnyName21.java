package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnyName21 extends AnyName {
  public AnyName21(ASTStringNode continue_kw, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("continue_kw", continue_kw)
    }, firstToken, lastToken);
  }
  public AnyName21(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnyName21(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getContinue_kw() {
    return ((PropertyOne<ASTStringNode>)getProperty("continue_kw")).getValue();
  }
}
