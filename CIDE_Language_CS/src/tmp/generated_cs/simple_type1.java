package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class simple_type1 extends simple_type {
  public simple_type1(numeric_type numeric_type, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<numeric_type>("numeric_type", numeric_type)
    }, firstToken, lastToken);
  }
  public simple_type1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new simple_type1(cloneProperties(),firstToken,lastToken);
  }
  public numeric_type getNumeric_type() {
    return ((PropertyOne<numeric_type>)getProperty("numeric_type")).getValue();
  }
}
