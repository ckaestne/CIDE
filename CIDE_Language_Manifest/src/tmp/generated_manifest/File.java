package tmp.generated_manifest;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class File extends GenASTNode implements ISourceFile {
  public File(ArrayList<Line> line, ASTStringNode eof, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOneOrMore<Line>("line", line),
      new PropertyOne<ASTStringNode>("eof", eof)
    }, firstToken, lastToken);
  }
  public File(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new File(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<Line> getLine() {
    return ((PropertyOneOrMore<Line>)getProperty("line")).getValue();
  }
  public ASTStringNode getEof() {
    return ((PropertyOne<ASTStringNode>)getProperty("eof")).getValue();
  }
}
