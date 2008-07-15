package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class qconop2 extends qconop {
  public qconop2(gconsym gconsym, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<gconsym>("gconsym", gconsym)
    }, firstToken, lastToken);
  }
  public qconop2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new qconop2(cloneProperties(),firstToken,lastToken);
  }
  public gconsym getGconsym() {
    return ((PropertyOne<gconsym>)getProperty("gconsym")).getValue();
  }
}
