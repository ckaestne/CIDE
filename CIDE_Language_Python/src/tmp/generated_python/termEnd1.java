package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class termEnd1 extends termEnd {
  public termEnd1(factor factor, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<factor>("factor", factor)
    }, firstToken, lastToken);
  }
  public termEnd1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new termEnd1(cloneProperties(),firstToken,lastToken);
  }
  public factor getFactor() {
    return ((PropertyOne<factor>)getProperty("factor")).getValue();
  }
}
