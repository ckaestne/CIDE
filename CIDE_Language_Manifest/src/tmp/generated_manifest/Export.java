package tmp.generated_manifest;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Export extends GenASTNode {
  public Export(ArrayList<ASTStringNode> packagedcl, ASTStringNode packageend, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<ASTStringNode>("packagedcl", packagedcl),
      new PropertyOne<ASTStringNode>("packageend", packageend)
    }, firstToken, lastToken);
  }
  public Export(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Export(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<ASTStringNode> getPackagedcl() {
    return ((PropertyList<ASTStringNode>)getProperty("packagedcl")).getValue();
  }
  public ASTStringNode getPackageend() {
    return ((PropertyOne<ASTStringNode>)getProperty("packageend")).getValue();
  }
}
