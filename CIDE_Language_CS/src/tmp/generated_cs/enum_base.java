package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class enum_base extends GenASTNode {
  public enum_base(integral_type integral_type, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<integral_type>("integral_type", integral_type)
    }, firstToken, lastToken);
  }
  public enum_base(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new enum_base(cloneProperties(),firstToken,lastToken);
  }
  public integral_type getIntegral_type() {
    return ((PropertyOne<integral_type>)getProperty("integral_type")).getValue();
  }
}
