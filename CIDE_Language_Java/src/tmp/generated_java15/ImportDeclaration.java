package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ImportDeclaration extends GenASTNode {
  public ImportDeclaration(ASTTextNode text1, Name name, ASTTextNode text2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTTextNode>("text1", text1),
      new PropertyOne<Name>("name", name),
      new PropertyZeroOrOne<ASTTextNode>("text2", text2)
    }, firstToken, lastToken);
  }
  public ImportDeclaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ImportDeclaration(cloneProperties(),firstToken,lastToken);
  }
  public ASTTextNode getText1() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text1")).getValue();
  }
  public Name getName() {
    return ((PropertyOne<Name>)getProperty("name")).getValue();
  }
  public ASTTextNode getText2() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text2")).getValue();
  }
}
