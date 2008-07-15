package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class javacc_options extends GenASTNode {
  public javacc_options(ArrayList<option_binding> option_binding, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOneOrMore<option_binding>("option_binding", option_binding)
    }, firstToken, lastToken);
  }
  public javacc_options(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new javacc_options(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<option_binding> getOption_binding() {
    return ((PropertyOneOrMore<option_binding>)getProperty("option_binding")).getValue();
  }
}
