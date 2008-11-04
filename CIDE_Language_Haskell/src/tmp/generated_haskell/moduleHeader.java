package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class moduleHeader extends GenASTNode {
  public moduleHeader(naam naam, exports exports, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<naam>("naam", naam),
      new PropertyZeroOrOne<exports>("exports", exports)
    }, firstToken, lastToken);
  }
  public moduleHeader(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new moduleHeader(cloneProperties(),firstToken,lastToken);
  }
  public naam getNaam() {
    return ((PropertyOne<naam>)getProperty("naam")).getValue();
  }
  public exports getExports() {
    return ((PropertyZeroOrOne<exports>)getProperty("exports")).getValue();
  }
}
