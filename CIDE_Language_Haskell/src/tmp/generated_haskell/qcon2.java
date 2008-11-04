package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class qcon2 extends qcon {
  public qcon2(gconsym gconsym, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<gconsym>("gconsym", gconsym)
    }, firstToken, lastToken);
  }
  public qcon2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new qcon2(cloneProperties(),firstToken,lastToken);
  }
  public gconsym getGconsym() {
    return ((PropertyOne<gconsym>)getProperty("gconsym")).getValue();
  }
}
