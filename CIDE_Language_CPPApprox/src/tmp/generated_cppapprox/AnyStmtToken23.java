package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnyStmtToken23 extends AnyStmtToken {
  public AnyStmtToken23(Modifier modifier, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Modifier>("modifier", modifier)
    }, firstToken, lastToken);
  }
  public AnyStmtToken23(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnyStmtToken23(cloneProperties(),firstToken,lastToken);
  }
  public Modifier getModifier() {
    return ((PropertyOne<Modifier>)getProperty("modifier")).getValue();
  }
}
