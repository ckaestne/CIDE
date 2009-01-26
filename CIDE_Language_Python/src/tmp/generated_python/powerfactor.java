package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class powerfactor extends GenASTNode {
  public powerfactor(atomtrailer atomtrailer, ArrayList<factor> factor, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<atomtrailer>("atomtrailer", atomtrailer),
      new PropertyZeroOrMore<factor>("factor", factor)
    }, firstToken, lastToken);
  }
  public powerfactor(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new powerfactor(cloneProperties(),firstToken,lastToken);
  }
  public atomtrailer getAtomtrailer() {
    return ((PropertyOne<atomtrailer>)getProperty("atomtrailer")).getValue();
  }
  public ArrayList<factor> getFactor() {
    return ((PropertyZeroOrMore<factor>)getProperty("factor")).getValue();
  }
}
