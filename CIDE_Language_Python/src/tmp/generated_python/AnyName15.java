package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnyName15 extends AnyName {
  public AnyName15(ASTStringNode def, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("def", def)
    }, firstToken, lastToken);
  }
  public AnyName15(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnyName15(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getDef() {
    return ((PropertyOne<ASTStringNode>)getProperty("def")).getValue();
  }
}
