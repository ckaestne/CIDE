package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class predefined_type1 extends predefined_type {
  public predefined_type1(simple_type simple_type, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<simple_type>("simple_type", simple_type)
    }, firstToken, lastToken);
  }
  public predefined_type1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new predefined_type1(cloneProperties(),firstToken,lastToken);
  }
  public simple_type getSimple_type() {
    return ((PropertyOne<simple_type>)getProperty("simple_type")).getValue();
  }
}
