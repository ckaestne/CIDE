package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class base_specification extends GenASTNode {
  public base_specification(ArrayList<base_specifier> base_specifier, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<base_specifier>("base_specifier", base_specifier)
    }, firstToken, lastToken);
  }
  public base_specification(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new base_specification(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<base_specifier> getBase_specifier() {
    return ((PropertyList<base_specifier>)getProperty("base_specifier")).getValue();
  }
}
