package tmp.generated_antlr;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class altList extends GenASTNode {
  public altList(ArrayList<altRewrite> altRewrite, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<altRewrite>("altRewrite", altRewrite)
    }, firstToken, lastToken);
  }
  public altList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new altList(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<altRewrite> getAltRewrite() {
    return ((PropertyList<altRewrite>)getProperty("altRewrite")).getValue();
  }
}
