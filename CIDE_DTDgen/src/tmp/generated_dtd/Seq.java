package tmp.generated_dtd;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Seq extends GenASTNode {
  public Seq(ArrayList<Cp> cp, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<Cp>("cp", cp)
    }, firstToken, lastToken);
  }
  public Seq(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Seq(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<Cp> getCp() {
    return ((PropertyList<Cp>)getProperty("cp")).getValue();
  }
}
