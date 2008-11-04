package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ImportDeclaration extends GenASTNode {
  public ImportDeclaration(ASTTextNode text481, Name name, ASTTextNode text482, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTTextNode>("text481", text481),
      new PropertyOne<Name>("name", name),
      new PropertyZeroOrOne<ASTTextNode>("text482", text482)
    }, firstToken, lastToken);
  }
  public ImportDeclaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ImportDeclaration(cloneProperties(),firstToken,lastToken);
  }
  public ASTTextNode getText481() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text481")).getValue();
  }
  public Name getName() {
    return ((PropertyOne<Name>)getProperty("name")).getValue();
  }
  public ASTTextNode getText482() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text482")).getValue();
  }
}
