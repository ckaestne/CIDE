package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class qvarop2 extends qvarop {
  public qvarop2(qvarsym qvarsym, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<qvarsym>("qvarsym", qvarsym)
    }, firstToken, lastToken);
  }
  public qvarop2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new qvarop2(cloneProperties(),firstToken,lastToken);
  }
  public qvarsym getQvarsym() {
    return ((PropertyOne<qvarsym>)getProperty("qvarsym")).getValue();
  }
}
