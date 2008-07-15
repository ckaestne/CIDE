package tmp.generated_capprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnyStmtToken28 extends AnyStmtToken {
  public AnyStmtToken28(PPOtherIgnore pPOtherIgnore, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<PPOtherIgnore>("pPOtherIgnore", pPOtherIgnore)
    }, firstToken, lastToken);
  }
  public AnyStmtToken28(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnyStmtToken28(cloneProperties(),firstToken,lastToken);
  }
  public PPOtherIgnore getPPOtherIgnore() {
    return ((PropertyOne<PPOtherIgnore>)getProperty("pPOtherIgnore")).getValue();
  }
}
