package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ModuleNaamPrefix extends GenASTNode {
  public ModuleNaamPrefix(ASTStringNode constructor_id, ArrayList<ASTStringNode> constructor_id1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("constructor_id", constructor_id),
      new PropertyZeroOrMore<ASTStringNode>("constructor_id1", constructor_id1)
    }, firstToken, lastToken);
  }
  public ModuleNaamPrefix(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ModuleNaamPrefix(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getConstructor_id() {
    return ((PropertyOne<ASTStringNode>)getProperty("constructor_id")).getValue();
  }
  public ArrayList<ASTStringNode> getConstructor_id1() {
    return ((PropertyZeroOrMore<ASTStringNode>)getProperty("constructor_id1")).getValue();
  }
}
