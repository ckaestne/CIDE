package tmp.generated_property;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Document extends GenASTNode implements ISourceFile {
  public Document(ArrayList<ASTStringNode> line, ASTStringNode eof, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrMore<ASTStringNode>("line", line),
      new PropertyOne<ASTStringNode>("eof", eof)
    }, firstToken, lastToken);
  }
  public Document(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Document(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<ASTStringNode> getLine() {
    return ((PropertyZeroOrMore<ASTStringNode>)getProperty("line")).getValue();
  }
  public ASTStringNode getEof() {
    return ((PropertyOne<ASTStringNode>)getProperty("eof")).getValue();
  }
}
