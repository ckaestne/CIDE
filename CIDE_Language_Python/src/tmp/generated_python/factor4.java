package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class factor4 extends factor {
  public factor4(powerfactor powerfactor, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<powerfactor>("powerfactor", powerfactor)
    }, firstToken, lastToken);
  }
  public factor4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new factor4(cloneProperties(),firstToken,lastToken);
  }
  public powerfactor getPowerfactor() {
    return ((PropertyOne<powerfactor>)getProperty("powerfactor")).getValue();
  }
}
