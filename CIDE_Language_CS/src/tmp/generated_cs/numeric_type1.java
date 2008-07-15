package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class numeric_type1 extends numeric_type {
  public numeric_type1(integral_type integral_type, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<integral_type>("integral_type", integral_type)
    }, firstToken, lastToken);
  }
  public numeric_type1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new numeric_type1(cloneProperties(),firstToken,lastToken);
  }
  public integral_type getIntegral_type() {
    return ((PropertyOne<integral_type>)getProperty("integral_type")).getValue();
  }
}
