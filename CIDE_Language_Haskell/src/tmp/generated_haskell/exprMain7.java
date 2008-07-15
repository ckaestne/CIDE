package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class exprMain7 extends exprMain {
  public exprMain7(altSpecialSemiList altSpecialSemiList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<altSpecialSemiList>("altSpecialSemiList", altSpecialSemiList)
    }, firstToken, lastToken);
  }
  public exprMain7(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new exprMain7(cloneProperties(),firstToken,lastToken);
  }
  public altSpecialSemiList getAltSpecialSemiList() {
    return ((PropertyOne<altSpecialSemiList>)getProperty("altSpecialSemiList")).getValue();
  }
}
