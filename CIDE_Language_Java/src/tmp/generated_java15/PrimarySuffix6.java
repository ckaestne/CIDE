package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class PrimarySuffix6 extends PrimarySuffix {
  public PrimarySuffix6(Arguments arguments, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Arguments>("arguments", arguments)
    }, firstToken, lastToken);
  }
  public PrimarySuffix6(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new PrimarySuffix6(cloneProperties(),firstToken,lastToken);
  }
  public Arguments getArguments() {
    return ((PropertyOne<Arguments>)getProperty("arguments")).getValue();
  }
}
