package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class gconsym2 extends gconsym {
  public gconsym2(qconsym qconsym, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<qconsym>("qconsym", qconsym)
    }, firstToken, lastToken);
  }
  public gconsym2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new gconsym2(cloneProperties(),firstToken,lastToken);
  }
  public qconsym getQconsym() {
    return ((PropertyOne<qconsym>)getProperty("qconsym")).getValue();
  }
}
