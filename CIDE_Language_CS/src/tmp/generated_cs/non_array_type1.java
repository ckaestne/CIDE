package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class non_array_type1 extends non_array_type {
  public non_array_type1(predefined_type predefined_type, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<predefined_type>("predefined_type", predefined_type)
    }, firstToken, lastToken);
  }
  public non_array_type1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new non_array_type1(cloneProperties(),firstToken,lastToken);
  }
  public predefined_type getPredefined_type() {
    return ((PropertyOne<predefined_type>)getProperty("predefined_type")).getValue();
  }
}
