package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class context1 extends context {
  public context1(klasse klasse, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<klasse>("klasse", klasse)
    }, firstToken, lastToken);
  }
  public context1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new context1(cloneProperties(),firstToken,lastToken);
  }
  public klasse getKlasse() {
    return ((PropertyOne<klasse>)getProperty("klasse")).getValue();
  }
}
