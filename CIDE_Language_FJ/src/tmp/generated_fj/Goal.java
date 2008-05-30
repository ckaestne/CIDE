package tmp.generated_fj;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Goal extends GenASTNode implements ISourceFile {
  public Goal(TypeDeclaration typeDeclaration, ASTStringNode eof, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<TypeDeclaration>("typeDeclaration", typeDeclaration),
      new PropertyOne<ASTStringNode>("eof", eof)
    }, firstToken, lastToken);
  }
  public Goal(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Goal(cloneProperties(),firstToken,lastToken);
  }
  public TypeDeclaration getTypeDeclaration() {
    return ((PropertyOne<TypeDeclaration>)getProperty("typeDeclaration")).getValue();
  }
  public ASTStringNode getEof() {
    return ((PropertyOne<ASTStringNode>)getProperty("eof")).getValue();
  }
}
