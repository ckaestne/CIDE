package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class numeric_type2 extends numeric_type {
  public numeric_type2(floating_point_type floating_point_type, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<floating_point_type>("floating_point_type", floating_point_type)
    }, firstToken, lastToken);
  }
  public numeric_type2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new numeric_type2(cloneProperties(),firstToken,lastToken);
  }
  public floating_point_type getFloating_point_type() {
    return ((PropertyOne<floating_point_type>)getProperty("floating_point_type")).getValue();
  }
}
