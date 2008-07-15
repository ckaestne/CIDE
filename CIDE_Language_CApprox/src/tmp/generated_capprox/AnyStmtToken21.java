package tmp.generated_capprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnyStmtToken21 extends AnyStmtToken {
  public AnyStmtToken21(Modifier modifier, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Modifier>("modifier", modifier)
    }, firstToken, lastToken);
  }
  public AnyStmtToken21(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnyStmtToken21(cloneProperties(),firstToken,lastToken);
  }
  public Modifier getModifier() {
    return ((PropertyOne<Modifier>)getProperty("modifier")).getValue();
  }
}
